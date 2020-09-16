package Client;

import dependencies.Command;

import java.util.ArrayList;

public class CommandCreator {
    private final StudyGroupCreator studyGroupCreator = new StudyGroupCreator();

    public Command create(String command) {
        return new Command(command);
    }

//    public Command create(String name){
//        if (name.equals("add")) {
//            return new Command(name, studyGroupCreator.constructor(0L));
//        } else {
//            return new Command(name);
//        }
//    }
//
//    public Command create(String name, Long id){
//        if (name.equals("update")) {
//            return new Command(name, studyGroupCreator.constructor(id));
//        } else {
//            return new Command(name, id);
//        }
//    }
//
//    public Command create(String name, String arg){
//        return new Command(name, arg);
//    }
//
//    public Command create(String name, int j, ArrayList<String[]> script){
//        if (name.equals("add") || name.equals("update")) {
//            return new Command(name, studyGroupCreator.constructor(j, script));
//        } else {
//            return new Command(name);
//        }
//    }
}