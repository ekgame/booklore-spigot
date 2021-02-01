package lt.ekgame.booklore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookLoreEvents implements Listener {

    public BookLore plugin;

    public BookLoreEvents(BookLore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrepareAnvilEvent(PrepareAnvilEvent event) {
        ItemStack input = event.getInventory().getItem(0);
        ItemStack addition = event.getInventory().getItem(1);

        if (canApplyBookText(input, addition)) {
            ItemStack newItem = input.clone();
            ItemMeta itemMeta = newItem.getItemMeta();
            BookMeta bootMeta = (BookMeta)addition.getItemMeta();

            if (itemMeta != null && bootMeta != null) {
                List<String> lines = bootMeta.getPages().stream()
                    .flatMap(page -> Arrays.stream(page.split("\\r?\\n")))
                    .flatMap(line -> formatLines(line, plugin.getMaxLineLength()).stream())
                    .map(line -> plugin.getLorePrefix() + line)
                    .limit(plugin.getMaxLines())
                    .collect(Collectors.toList());

                if (bootMeta.hasAuthor() && plugin.isAddAuthorLine()) {
                    lines.add(plugin.getAuthorLinePrefix() + bootMeta.getAuthor());
                }

                String renameText = event.getInventory().getRenameText();
                int expCost = plugin.getExpAmount();
                if (renameText != null) {
                    itemMeta.setDisplayName(renameText);
                    expCost += plugin.getExpAmountRename();
                }

                itemMeta.setLore(lines);
                newItem.setItemMeta(itemMeta);
                event.getInventory().setRepairCost(expCost);
                event.setResult(newItem);
            }
        }
    }

    private boolean canApplyBookText(ItemStack input, ItemStack addition) {
        return input != null && addition != null
            && (addition.getType() == Material.WRITABLE_BOOK || addition.getType() == Material.WRITTEN_BOOK);
    }

    private List<String> formatLines(String target, int maxLength) {
        BreakIterator boundary = BreakIterator.getLineInstance();
        boundary.setText(target);
        int start = boundary.first();
        int end = boundary.next();
        int lineLength = 0;

        List<String> lines = new ArrayList<>();
        List<String> buffer = new ArrayList<>();

        while (end != BreakIterator.DONE) {
            String word = target.substring(start,end);
            lineLength = lineLength + word.length();
            if (lineLength >= maxLength) {
                lines.add(String.join("", buffer));
                buffer.clear();
                lineLength = word.length();
            }
            buffer.add(word);
            start = end;
            end = boundary.next();
        }

        lines.add(String.join("", buffer));
        return lines;
    }
}
