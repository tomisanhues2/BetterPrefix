package me.tomisanhues.betterprefix.scoreboard;

import me.tomisanhues.betterprefix.BetterPrefix;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Set;

public class ScoreboardHandler {

        private Scoreboard scoreboard;

        private BetterPrefix betterprefix;

        public ScoreboardHandler(BetterPrefix betterprefix) {
            this.betterprefix = betterprefix;
        }

        public void createScoreboard() {
            if (scoreboard != null) {
                scoreboard = betterprefix.getServer().getScoreboardManager().getNewScoreboard();
            }
            Set<Group> groups = BetterPrefix.getApi().getGroupManager().getLoadedGroups();
            betterprefix.getLogger().info("Number of groups: " + groups.size());
            for (int x =0; x < groups.size(); x++) {
                betterprefix.getLogger().info("Group: " + ((Group) groups.toArray()[x]).getName());
                betterprefix.getLogger().info("Weight is" + ((Group) groups.toArray()[x]).getWeight());
            }

        }

}
