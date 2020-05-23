package Command;

import java.io.IOException;
import java.sql.SQLException;

public interface Command {
	/**
     * executer une commande.
	 * @throws SQLException 
	 * @throws IOException 
     */
    void execute() throws IOException, SQLException;

}
