package by.training.homework7.controller.command;

import by.training.homework7.controller.command.impl.AddBookCommand;
import by.training.homework7.controller.command.impl.DeleteBookCommand;
import by.training.homework7.controller.command.impl.FindAllCommand;
import by.training.homework7.controller.command.impl.SearchByTagCommand;

public enum CommandType {
    ADD_BOOK {
        {
            this.command = new AddBookCommand();
        }
    },
    DELETE_BOOK {
        {
            this.command = new DeleteBookCommand();
        }
    },
    SEARCH_BY_TAG {
        {
            this.command = new SearchByTagCommand();
        }
    },
    FIND_ALL {
        {
            this.command = new FindAllCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }
}
