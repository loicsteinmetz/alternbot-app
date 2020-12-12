package controllers.commands;

import controllers.Runner;
import controllers.commands.schedule.ScheduleCommandsHandler;

/**
 * Contrôleur permettant d'initialiser la gestion des commandes utilisateur.
 */
public class CommandsController extends Runner<CommandsHandler> {

  @Override
  public Runner<CommandsHandler> init() {
    runnables.add(new ScheduleCommandsHandler().init());
    return this;
  }
}