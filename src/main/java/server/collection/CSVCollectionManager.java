package server.collection;

import shared.model.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

public class CSVCollectionManager {
    private final String filePath;
    private final String delimiter;
    private final List<LabWork> dataCollection;

    public CSVCollectionManager(String filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
        this.dataCollection = new ArrayList<>();
        loadDataFromFile();
    }

    public List<LabWork> getDataCollectionLabWork() {
        return Collections.unmodifiableList(dataCollection);
    }

    public void loadDataFromFile() {
        try (Scanner scanner = new Scanner(new File(filePath), StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Пропускаем заголовок
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(delimiter);

                if (values.length >= 14) {
                    try {
                        LabWork labWork = parseLabWork(values);
                        dataCollection.add(labWork);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Ошибка парсинга строки: " + line);
                    }
                }
            }
            System.out.println("Данные успешно загружены из файла: " + filePath);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filePath);
        } catch (Exception e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private LabWork parseLabWork(String[] values) {
        LabWork labWork = new LabWork();
        labWork.setName(values[1]);

        Coordinates coordinates = new Coordinates();
        coordinates.setX(Float.parseFloat(values[2]));
        coordinates.setY(Float.parseFloat(values[3]));
        labWork.setCoordinates(coordinates);

        labWork.setCreationDate(LocalDate.parse(values[4]));
        labWork.setMinimalPoint(Double.parseDouble(values[5]));
        labWork.setDifficulty(Difficulty.valueOf(values[6]));

        Person author = new Person();
        author.setName(values[7]);
        author.setWeight(Integer.parseInt(values[8]));
        author.setEyeColor(Color.valueOf(values[9]));
        author.setHairColor(values[10].isEmpty() ? null : Color.valueOf(values[10]));
        author.setNationality(values[11].isEmpty() ? null : Country.valueOf(values[11]));

        Location location = new Location();
        location.setX(Long.parseLong(values[12]));
        location.setY(Integer.parseInt(values[13]));
        location.setZ(Float.parseFloat(values[14]));
        location.setName(values[15]);
        author.setLocation(location);

        labWork.setAuthor(author);
        return labWork;
    }

    public boolean saveDataToFile(boolean appendToFile) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath, appendToFile), StandardCharsets.UTF_8))) {

            // Записываем заголовок
            writer.write(String.join(delimiter,
                    "id", "name", "coordinates_x", "coordinates_y", "creationDate",
                    "minimalPoint", "difficulty", "author_name", "author_weight",
                    "author_eyeColor", "author_hairColor", "author_nationality",
                    "location_x", "location_y", "location_z", "location_name"));
            writer.newLine();

            // Записываем данные
            for (LabWork labWork : dataCollection) {
                writer.write(convertToCSVLine(labWork));
                writer.newLine();
            }

            System.out.println("Данные успешно сохранены в файл: " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка сохранения в файл: " + e.getMessage());
            return false;
        }
    }

    private String convertToCSVLine(LabWork labWork) {
        return String.join(delimiter,
                labWork.getId().toString(),
                labWork.getName(),
                String.valueOf(labWork.getCoordinates().getX()),
                String.valueOf(labWork.getCoordinates().getY()),
                labWork.getCreationDate().toString(),
                labWork.getMinimalPoint().toString(),
                labWork.getDifficulty().name(),
                labWork.getAuthor().getName(),
                labWork.getAuthor().getWeight().toString(),
                labWork.getAuthor().getEyeColor().name(),
                labWork.getAuthor().getHairColor() != null ? labWork.getAuthor().getHairColor().name() : "",
                labWork.getAuthor().getNationality() != null ? labWork.getAuthor().getNationality().name() : "",
                labWork.getAuthor().getLocation().getX().toString(),
                labWork.getAuthor().getLocation().getY().toString(),
                labWork.getAuthor().getLocation().getZ().toString(),
                labWork.getAuthor().getLocation().getName()
        );
    }

    public void addLabWork(LabWork labWork) {
        dataCollection.add(labWork);
    }

    public void removeLabWork(LabWork labWork) {
        dataCollection.remove(labWork);
    }

    public void clearCollection() {
        dataCollection.clear();
    }
}