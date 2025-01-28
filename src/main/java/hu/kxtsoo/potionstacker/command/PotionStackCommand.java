package hu.kxtsoo.potionstacker.command;

import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import hu.kxtsoo.potionstacker.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.PotionMeta;

@Command(value="potionstacker", alias = {"potionstack", "potstack", "stackpot", "pstack"})
@Permission("axpotionstacker.stack.use")
public class PotionStackCommand extends BaseCommand {

    @Default
    public void stack(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Ez a parancs csak játékosok által futtatható!");
            return;
        }

        PlayerInventory inventory = player.getInventory();

        ItemStack[] contents = inventory.getContents();
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];

            if (item != null && item.getType() == Material.POTION && item.getItemMeta() instanceof PotionMeta potionMeta) {
                for (int j = i + 1; j < contents.length; j++) {
                    ItemStack otherItem = contents[j];

                    if (otherItem != null && otherItem.getType() == Material.POTION
                            && otherItem.getItemMeta() instanceof PotionMeta
                            && potionMeta.equals(otherItem.getItemMeta())) {

                        int totalAmount = item.getAmount() + otherItem.getAmount();

                        if (totalAmount <= item.getMaxStackSize()) {
                            item.setAmount(totalAmount);
                            contents[j] = null;
                        } else {
                            item.setAmount(item.getMaxStackSize());
                            otherItem.setAmount(totalAmount - item.getMaxStackSize());
                        }
                    }
                }
            }
        }

        inventory.setContents(contents);
        player.sendMessage(ChatUtil.colorizeHex("&aSikeresen össze lettek stackelve a potionjaid!"));
    }
}
