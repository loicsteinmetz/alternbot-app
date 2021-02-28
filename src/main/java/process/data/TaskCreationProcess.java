package process.data;

import exceptions.InvalidDataException;
import exceptions.MemberAccessException;
import models.Server;
import models.Task;
import net.dv8tion.jda.api.entities.Member;

import java.util.Date;

/**
 * Process de création d'une tâche par un utilisateur autorisé.
 */
public class TaskCreationProcess extends TaskAccessor {

  /**
   * @param description description de la tâche
   * @param dueDate     date de rendu de la tâche(jour)
   * @param dueTime     date de rendu de la tâche(heure)
   * @param member      utilisateur ayant initié l'opération
   * @param server      serveur à partir du quel est émise la demande de création
   * @return true si l'opération a pu être réalisée
   * @throws MemberAccessException l'utilisateur n'est pas autorisé à créer les tâches
   * @throws InvalidDataException  les informations saisies sont invalides
   */
  public boolean create(String description, Date dueDate, Date dueTime, Member member, Server server) throws MemberAccessException, InvalidDataException {
    Task task = validateTask(description, dueDate, dueTime, server);
    if (isMemberAuthorized(member)) {
      task.create();
      return true;
    } else {
      throw new MemberAccessException();
    }
  }

  /**
   * Vérification de la validité des attributs de la tâche
   *
   * @param description de la tâche
   * @param dueDate     date de rendu de la tâche(jour)
   * @param dueTime     date de rendu de la tâche(heure)
   * @return Task l'instance de la tâche si elle est valide
   * @throws InvalidDataException les informations saisies sont invalides
   */
  private Task validateTask(String description, Date dueDate, Date dueTime, Server server) throws InvalidDataException {
    if (description == null || dueDate == null || dueTime == null) {
      throw new InvalidDataException();
    } else {
      return new Task(description, dueDate, dueTime, server);
    }
  }
}
