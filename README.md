# Book Lore

A Spigot plugin that allows using the anvil to apply book contents as item lore.

Created for a Vanilla survival server as a mechanic to add lore to items, personalize them, create event prizes, souvenirs, give the roleplayers a new mechanic to create and evolve stories.

Video demo: https://youtu.be/u1ogHb9iLls

## Configuration

```yml
# The amount of experience it costs to apply the lore to an item. The minimum amount is 1.
# 0 is invalid because the game will not allow you to take out the custom item.
exp-amount: 1

# Additional cost for renaming the item.
exp-amount-rename: 0

# If a line is too long to fit into this character limit - it will be wrapped to
# the next line with respect to word boundaries.
max-line-length: 38

# Limit the total amount of lines of lore allowed.
max-lines: 24

# The prefix for each line of the lore, used to change color and style of the text.
# Formatting codes: https://minecraft.gamepedia.com/Formatting_codes
lore-line-prefix: §r§7§o

# If the book is signed, the book author's name may be added to the bottom of the lore.
# The author's name can not be faked, so the signed name can serve as a sign of authenticity.
add-signed-name: true

# The prefix for the author line, to make it stand out.
# Formatting codes: https://minecraft.gamepedia.com/Formatting_codes
author-line-prefix: §r§e§l
```

## Installation

1. Download the latest release of the plugin and copy the jar file it to your server's plugin folder.
2. Restart the server to generate the configuration file.
3. Configure the plugin as required and restart the server again.

## Known issues

* Clicking on the anvil's text box makes the item disappear. The item will still be crafted if you interact with the output slot.
* Sometimes after crafting the item, it may disappear. Closing and reopening the inventory will display the item again.
