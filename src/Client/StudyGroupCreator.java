package Client;

import Collection.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudyGroupCreator {
    public StudyGroup constructor(Long id) {
        while (true) {
            try {
                Scanner field = new Scanner(System.in);
                System.out.println("Input count of students");
                boolean flag = true;
                String studentsCountString = field.nextLine();

                while (flag) {
                    if (studentsCountString.trim().length()==0) throw new InputMismatchException();
                    if (!isDigit(studentsCountString)) {
                        System.out.println("! Count of students must be a number !");
                        studentsCountString = field.nextLine();
                    } else {
                        if (Long.parseLong(studentsCountString)<=0) {
                            System.out.println("! Number must be > 0, try again !");
                            studentsCountString = field.nextLine();
                        } else flag = false;
                    }
                }
                Long studentsCount = Long.parseLong(studentsCountString);
                System.out.println("Input group's name");
                String name = field.nextLine();

                if (name.trim().length()==0) {
                    System.out.println("! Empty string entered !");
                    throw new InputMismatchException();
                }
                flag = true;
                System.out.println("Input x coordinate:");
                String xString = field.nextLine();

                while (flag) {
                    if (xString.trim().length() == 0) throw new InputMismatchException();
                    if (!isDigit(xString)) {
                        System.out.println("! x must be a number !");
                        xString = field.nextLine();
                    } else {
                        if (Integer.parseInt(xString) <= -18) {
                            System.out.println("! x must be > -18, try again !");
                            xString = field.nextLine();
                        } else flag = false;
                    }
                }
                flag = true;
                Integer x = Integer.parseInt(xString);
                System.out.println("Input y coordinate:");
                String yString = field.nextLine();

                while (flag) {
                    if (yString.trim().length()==0) throw new InputMismatchException();
                    if (!isDigit(yString)){
                        System.out.println("! y must be a number !");
                        yString = field.nextLine();
                    } else{
                        if (Long.parseLong(yString)>950){
                            System.out.println("! y must be <= 950, try again !");
                            yString = field.nextLine();
                        } else flag = false;
                    }
                }
                Long y = Long.parseLong(yString);
                System.out.println("Input amount of expelled students:");
                String expelledStudentsString = field.nextLine();
                flag = true;
                while (flag) {
                    if (expelledStudentsString.trim().length()==0) throw new InputMismatchException();
                    if (!isDigit(expelledStudentsString)){
                        System.out.println("! Amount of students must be a number !");
                        expelledStudentsString = field.nextLine();
                    } else{
                        if (Integer.parseInt(expelledStudentsString)<=0){
                            System.out.println("! Number must be > 0, try again !");
                            expelledStudentsString = field.nextLine();
                        } else flag = false;
                    }
                }
                Integer expelledStudents = Integer.parseInt(expelledStudentsString);
                flag = true;
                System.out.println("Input amount of should be expelled students:");
                String shouldBeExpelledStudentsString = field.nextLine();
                while (flag){
                    if (shouldBeExpelledStudentsString.trim().length()==0) throw new InputMismatchException();
                    if (!isDigit(shouldBeExpelledStudentsString)){
                        System.out.println("! Amount of students must be a number !");
                        shouldBeExpelledStudentsString = field.nextLine();
                    } else{
                        if (Integer.parseInt(shouldBeExpelledStudentsString)<=0){
                            System.out.println("! Number must be > 0, try again !");
                            shouldBeExpelledStudentsString = field.nextLine();
                        } else flag = false;
                    }
                }
                flag = true;
                Integer shouldBeExpelled = Integer.parseInt(shouldBeExpelledStudentsString);
                System.out.println("Choose and input semester of group: " + Arrays.toString(Semester.values()));
                String semesterEnumValue = field.nextLine();
                while (flag){
                    if (semesterEnumValue.trim().length()==0) throw new InputMismatchException();
                    if (!containsSemester(semesterEnumValue)){
                        System.out.println("! Choose one of these semesters !");
                        semesterEnumValue = field.nextLine();
                    } else flag = false;
                }
                Semester semester = Semester.valueOf(semesterEnumValue);
                System.out.println("!Input admin's name:");
                String nameGroupAdmin = field.nextLine();
                if (nameGroupAdmin.trim().length()==0){
                    System.out.println("!Empty string entered!");
                    throw new InputMismatchException();
                }
                flag = true;
                System.out.println("!Input admin's height:");
                String  heightString = field.nextLine();
                while (flag){
                    if (heightString.trim().length()==0) throw new InputMismatchException();
                    if (!isDigitFloat(heightString)){
                        System.out.println("! Input number !");
                        heightString = field.nextLine();
                    } else{
                        if (Float.parseFloat(heightString)<=0){
                            System.out.println("! height must be > 0 !");
                            heightString = field.nextLine();
                        } else flag = false;
                    }
                }
                float height = Float.parseFloat(heightString);
                System.out.println("!Input admin's weight:");
                flag = true;
                String weightString = field.nextLine();
                while (flag){
                    if (weightString.trim().length()==0) throw new InputMismatchException();
                    if (!isDigit(weightString)){
                        System.out.println("! Input number !");
                        weightString = field.nextLine();
                    } else if (Integer.parseInt(weightString)<=0){
                        System.out.println("! Weight must be > 0 !");
                        weightString = field.nextLine();
                    } else flag = false;
                }
                int weight = Integer.parseInt(weightString);
                System.out.println("!Choose and input admin's country: " + Arrays.toString(Country.values()));
                flag = true;
                String country = field.nextLine();
                while (flag){
                    if (country.trim().length()==0) throw new InputMismatchException();
                    if (!containsCountry(country)){
                        System.out.println("! Choose one of these country !");
                        country = field.nextLine();
                    } else flag = false;
                }
                Country nationality = Country.valueOf(country);
                Coordinates coordinates = new Coordinates(x, y);
                Person groupAdmin = new Person(nameGroupAdmin, height, weight, nationality);
                if (id!=0){
                    StudyGroup studyGroup = new StudyGroup(name, semester, coordinates, studentsCount, groupAdmin, shouldBeExpelled, expelledStudents);
                    studyGroup.setId(id);
                    return studyGroup;
                } else return new StudyGroup(name, semester, coordinates, studentsCount, groupAdmin, shouldBeExpelled, expelledStudents);
            } catch  (InputMismatchException e){
                System.out.println("! Input error !");
            }catch (IllegalArgumentException e){
                System.out.println("! Wrong format semester or country !");
            }
        }
    }
    private boolean isDigit(String s) throws NumberFormatException{
        try{
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private boolean isDigitFloat(String s) throws NumberFormatException{
        try{
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }


    private boolean containsSemester(String str) {

        for (Semester sem : Semester.values()) {
            if (sem.name().equals(str)) {
                return true;
            }
        }

        return false;
    }

    private boolean containsCountry(String str) {

        for (Country country : Country.values()) {
            if (country.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public StudyGroup constructor(int j, ArrayList<String[]> script) throws InputMismatchException{
        StudyGroup studyGroup = null;
        try {
            String studentsCountString = script.get(j + 1)[0];

            if (studentsCountString.trim().length() == 0) {
                throw new InputMismatchException();
            }

            if (!isDigit(studentsCountString)) {
                System.out.println("! students count must be number !");
                throw new InputMismatchException();
            } else if (Long.parseLong(studentsCountString) <= 0) {
                System.out.println("! students count must be > 0 !");
                throw new InputMismatchException();
            }

            Long studentsCount = Long.parseLong(studentsCountString);
            String name = script.get(j + 2)[0];

            if (name.trim().length() == 0) {
                System.out.println("! Empty string entered !");
                throw new InputMismatchException();
            }

            String xString = script.get(j + 3)[0];

            if (xString.trim().length()==0) {
                throw new InputMismatchException();
            }

            if (!isDigit(xString)){
                System.out.println("! x must be number !");
                throw new InputMismatchException();
            } else if (Integer.parseInt(xString) <= -18){
                System.out.println("! x must be > -18 !");
                throw new InputMismatchException();
            }

            Integer x = Integer.parseInt(xString);
            String yString = script.get(j + 4)[0];

            if (yString.trim().length()==0) {
                throw new InputMismatchException();
            }

            if (!isDigit(yString)){
                System.out.println("! y must be number !");
                throw new InputMismatchException();
            } else if (Long.parseLong(yString)>950){
                System.out.println("! y must be <= 950 !");
                throw new InputMismatchException();
            }

            Long y = Long.parseLong(yString);
            String expelledString = script.get(j + 5)[0];

            if (expelledString.trim().length()==0) {
                throw new InputMismatchException();
            }

            if (!isDigit(expelledString)){
                System.out.println("! count of expelled students must be number");
                throw new InputMismatchException();
            } else if (Integer.parseInt(expelledString)<=0){
                System.out.println("! Count of expelled students must be > 0");
                throw new InputMismatchException();
            }

            Integer expelledStudents = Integer.parseInt(expelledString);
            String shouldString = script.get(j + 6)[0];

            if (shouldString.trim().length()==0) {
                throw new InputMismatchException();
            }

            if (!isDigit(shouldString)) {
                System.out.println("! Count of should be expelled students must be a number !");
                throw new InputMismatchException();
            } else if (Integer.parseInt(shouldString)<=0){
                System.out.println("! Count of should be expelled students must be > 0 !");
            }

            Integer shouldBeExpelled = Integer.parseInt(shouldString);
            String semString = script.get(j + 7)[0];

            if (semString.trim().length()==0) {
                throw new InputMismatchException();
            } else if (!containsSemester(semString)) {
                System.out.println("! Wrong semester !");
                throw new InputMismatchException();
            }

            Semester semester = Semester.valueOf(semString);
            String nameGroupAdmin = script.get(j + 8)[0];

            if (nameGroupAdmin.trim().length() == 0) {
                throw new InputMismatchException();
            }

            String heightString = script.get(j + 9)[0];

            if (heightString.trim().length() == 0) {
                throw new InputMismatchException();
            }

            if (!isDigitFloat(heightString)){
                System.out.println("! height must be a number !");
                throw new InputMismatchException();
            } else if (Float.parseFloat(heightString) <= 0){
                System.out.println("! height must be > 0 !");
            }

            float height = Float.parseFloat(heightString);
            String weightString = script.get(j + 10)[0];

            if (weightString.trim().length() == 0) {
                throw new InputMismatchException();
            } else if (!isDigit(weightString)) {
                System.out.println("! weight must be a number !");
                throw new InputMismatchException();
            } else if (Integer.parseInt(weightString) <= 0){
                System.out.println("! weight must be > 0 !");
                throw new InputMismatchException();
            }

            int weight = Integer.parseInt(weightString);
            String country = script.get(j + 11)[0];

            if (country.trim().length()==0) {
                throw new InputMismatchException();
            } else if (!containsCountry(country)){
                System.out.println("! Wrong country in file !");
                throw new InputMismatchException();
            }

            Country nationality = Country.valueOf(country);
            Coordinates coordinates = new Coordinates(x, y);
            Person groupAdmin = new Person(nameGroupAdmin, height, weight, nationality);

            if (script.get(j)[0].equals("update")) {
                studyGroup = new StudyGroup(name, semester, coordinates, studentsCount, groupAdmin, shouldBeExpelled, expelledStudents);
                studyGroup.setId(Long.valueOf(script.get(j)[1]));
            } else {
                studyGroup = new StudyGroup(name, semester, coordinates, studentsCount, groupAdmin, shouldBeExpelled, expelledStudents);
            }
        } catch (InputMismatchException e){
            System.out.println("! Empty field in file !");
        }
        return studyGroup;
    }
}
