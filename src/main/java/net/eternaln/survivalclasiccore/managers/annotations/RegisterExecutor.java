package net.eternaln.survivalclasiccore.managers.annotations;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;

public class RegisterExecutor {

    public RegisterExecutor() {
        execute(new Reflections("net.eternaln.survivalclasiccore"));
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

            if (obj instanceof Listener) {
                Bukkit.getPluginManager().registerEvents((Listener) obj, SurvivalClasicCore.getInstance());
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

            SurvivalClasicCore.getCmdManager().registerCommand((BaseCommand) obj);
        }
    }
}