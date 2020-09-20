package dependencies.Commands;

import Collection.StudyGroup;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class UpdateCommand extends Commands{
    private StudyGroup argument;

    public UpdateCommand(String name, StudyGroup argument){
        this.name = name;
        this.argument = argument;
    }
    @Override
    public ArrayList<String> execute(){
        return manager.update(argument);
    }
}
