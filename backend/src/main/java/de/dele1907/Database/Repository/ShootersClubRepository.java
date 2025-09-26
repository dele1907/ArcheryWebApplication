package de.dele1907.Database.Repository;

import de.dele1907.Model.Club.ShootersClub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShootersClubRepository implements IBaseRepository<ShootersClub, String>{
    Logger LOGGER = LoggerFactory.getLogger(ShootersClubRepository.class);

    //Dummy Data
    private final List<ShootersClub> shootersClubs = new ArrayList<>();

    public ShootersClubRepository() {
        shootersClubs.add(new ShootersClub("4711", "BSC"));
        shootersClubs.add(new ShootersClub("815", "Gilde"));
    }

    @Override
    public List<ShootersClub> findAll() {
        if (shootersClubs.isEmpty()) {
            LOGGER.info("No shooters clubs found");
        }

        return shootersClubs;
    }

    @Override
    public Optional<ShootersClub> findById(String id) {
        for (var shootersClub : shootersClubs) {
            if (shootersClub.clubId().equals(id)) {
                return Optional.of(shootersClub);
            }
        }

        LOGGER.info("No shooters club found");
        return Optional.empty();
    }

    @Override
    public boolean deleteById(String id) {
        var removed = shootersClubs.remove(id);

        if (!removed) {
            LOGGER.info("No shooters club found to delete");

            return false;
        }

        LOGGER.info("Deleted shooters club {}", id);

        return true;
    }

    @Override
    public boolean save(ShootersClub entity) {
        LOGGER.info("Saving shooters club {}", entity.clubId());

        return shootersClubs.add(entity);
    }

    @Override
    public boolean update(ShootersClub entity) {
        for (int i = 0; i < shootersClubs.size(); i++) {
            if (shootersClubs.get(i).clubId().equals(entity.clubId())) {
                shootersClubs.set(i, entity);
                LOGGER.info("Updated shooters club {}", entity.clubName());

                return true;
            }
        }
        LOGGER.info("No shooters club found to update");

        return false;
    }
}
