package Client;

import dependencies.CommandObject;
import dependencies.CommandObjectCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("FieldCanBeLocal")
public class ScriptReader {
    private final CommandObjectCreator commandObjectCreator = new CommandObjectCreator();
    private ArrayList<String> rec = new ArrayList<>();
    private ArrayList<String[]> script;

    private static CommandObject commandObject;

    private final InetAddress serverAddress;
    private String scriptCommand;
    private String scriptArgs;

    private final Receiver receiver;
    private final Sender sender;

    private Scanner in;

    public ScriptReader(Receiver receiver, Sender sender, InetAddress serverAddress) {
        this.receiver = receiver;
        this.sender = sender;
        this.serverAddress = serverAddress;
    }

    public void executeScript(String file_path) {
        File file = new File(file_path);
        try {
            for (String str : rec) {
                if (file.getName().equals(str)) {
                    throw new InputMismatchException();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Recursion cannot work with the same file!");
            return;
        }

        rec.add(file.getName());

        try {
            script = new ArrayList<>();
            in = new Scanner(new File(file_path));

            while (in.hasNextLine()) {
                script.add(in.nextLine().split(" ", 2));
            }

            for (int j = 0; j < script.size(); j++) {
                scriptCommand = script.get(j)[0];
                scriptArgs = (script.get(j).length >= 2) ? script.get(j)[1] : null;

                try {
                    switch (scriptCommand) {
                        case "help":
                        case "info":
                        case "show":
                        case "clear":
                        case "max_by_expelled_students":
                        case "remove_first":
                        case "head":
                        case "history":
                        case "exit":
                            this.sender.sendMessage(commandObjectCreator.create(scriptCommand, new ArrayList<>()), this.serverAddress);
                            break;

                        case "add":
                            this.sender.sendMessage(commandObjectCreator.create(scriptCommand, new ArrayList<>(Collections.singleton(scriptArgs))), this.serverAddress);
                            break;

                        case "update":
                        case "remove_by_id":
                        case "execute_script":
                        case "filter_contains_name":
                            assert scriptArgs != null;
                            this.sender.sendMessage(commandObjectCreator.create(scriptCommand, new ArrayList<>(Collections.singleton(scriptArgs))), this.serverAddress);
                            break;
                    }

                    for (String line : this.receiver.handleMessage().getMessage()) {
                        System.out.println(line);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                rec.clear();
            }
        } catch (FileNotFoundException ignored) {

        }
    }
}
