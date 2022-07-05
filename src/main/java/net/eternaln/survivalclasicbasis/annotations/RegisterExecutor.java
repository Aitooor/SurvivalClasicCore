package net.eternaln.survivalclasicbasis.annotations;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class RegisterExecutor {

    public RegisterExecutor() {
        SurvivalClasicBasis.getInstance().getLogger().info("Registering commands and listeners...");
        execute(new Reflections("net.eternaln.survivalclasicbasis"));
        SurvivalClasicBasis.getInstance().getLogger().info("Commands and listeners registered.");
    }

    private void execute(Reflections reflections) {
        for (Class<?> clazz : reflections.getTypesAnnotatedWith(Register.class)) {
            Object obj = null;
            try {
                obj = clazz.getDeclaredConstructor().newInstance();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }

            SurvivalClasicBasis.getInstance().getLogger().info("Registering " + clazz.getSimpleName() + "...");

            if (obj instanceof Listener) {
                Bukkit.getPluginManager().registerEvents((Listener) obj, SurvivalClasicBasis.getInstance());
            }
        }

        for (Class<?> clazz : reflections.getTypesAnnotatedWith(CommandAlias.class)) {

            Object obj = null;
            try {
                obj = clazz.getDeclaredConstructor().newInstance();
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                e.printStackTrace();
                Bukkit.getLogger().info(clazz.getSimpleName());
            }

            SurvivalClasicBasis.getInstance().getLogger().info("Registering " + clazz.getSimpleName() + "...");

            SurvivalClasicBasis.getCmdManager().registerCommand((BaseCommand) obj);
        }
    }
}