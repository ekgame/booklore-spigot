package lt.ekgame.booklore;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class BookLore extends JavaPlugin {

    private int expAmount;
    private int expAmountRename;
    private int maxLineLength;
    private int maxLines;
    private String lorePrefix;
    private boolean addAuthorLine;
    private String authorLinePrefix;

    @Override
    public void onEnable() {
        loadConfiguration();

        getServer().getPluginManager().registerEvents(new BookLoreEvents(this), this);
    }

    @Override
    public void onDisable() {}

    private void loadConfiguration() {
        this.saveDefaultConfig();
        this.reloadConfig();

        this.expAmount = getConfig().getInt("exp-amount", 1);
        this.expAmountRename = getConfig().getInt("exp-amount-rename", 0);
        this.maxLineLength = getConfig().getInt("max-line-length", 38);
        this.maxLines = getConfig().getInt("max-lines", 24);
        this.lorePrefix = getConfig().getString("lore-line-prefix", "" + ChatColor.RESET + ChatColor.GRAY);
        this.addAuthorLine = getConfig().getBoolean("add-signed-name", false);
        this.authorLinePrefix = getConfig().getString("author-line-prefix", "" + ChatColor.RESET + ChatColor.YELLOW + ChatColor.BOLD);
    }

    public int getExpAmount() {
        return expAmount;
    }

    public int getExpAmountRename() {
        return expAmountRename;
    }

    public int getMaxLineLength() {
        return maxLineLength;
    }

    public int getMaxLines() {
        return maxLines;
    }

    public String getLorePrefix() {
        return lorePrefix;
    }

    public boolean isAddAuthorLine() {
        return addAuthorLine;
    }

    public String getAuthorLinePrefix() {
        return authorLinePrefix;
    }
}
