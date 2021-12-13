package me.snakeamazing.clans.clan;

import me.snakeamazing.clans.file.FileCreator;
import me.snakeamazing.clans.file.FileMatcher;
import me.snakeamazing.clans.storage.repository.ObjectRepository;
import me.snakeamazing.clans.storage.repository.RepositoryMatcher;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ClanManager {

    private final FileCreator config;
    private final ObjectRepository<Clan> clanObjectRepository;

    public ClanManager(FileMatcher matcher, RepositoryMatcher repositoryMatcher) {
        this.config = matcher.getFile("config");

        clanObjectRepository = (ObjectRepository<Clan>) repositoryMatcher.get(Clan.class);

    }

    public void createClan(String name, String prefix, Player leader) {

        if (clanObjectRepository.findOneByValue("name", name).isPresent()) {
            leader.sendMessage("Clan already exists.");
            return;
        }

        Clan clan = new Clan(name, prefix, leader.getName());

        leader.sendMessage("Clan created correctly");

        clanObjectRepository.save(clan);
    }

    public Clan findClanByLeader(String leader) {
        Optional<Clan> clan = clanObjectRepository.findOneByValue("leader", leader);

        return clan.orElse(null);

    }



}
