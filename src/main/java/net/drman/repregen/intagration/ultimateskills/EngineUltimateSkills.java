package net.drman.repregen.intagration.ultimateskills;

import com.massivecraft.massivecore.Engine;
import mc.ultimatecore.skills.HyperSkills;
import mc.ultimatecore.skills.listener.perks.DoubleItemPerks;
import mc.ultimatecore.skills.objects.Skill;
import mc.ultimatecore.skills.objects.SkillType;
import mc.ultimatecore.skills.objects.perks.Perk;
import mc.ultimatecore.skills.objects.xp.BlockXP;
import mc.ultimatecore.skills.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class EngineUltimateSkills extends Engine {
    private static EngineUltimateSkills i = new EngineUltimateSkills();
    public static EngineUltimateSkills get() {return i;}

    private static HyperSkills plugin;

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        if(active) {
            plugin = HyperSkills.getInstance();
        }
    }

    public void manageBlockPoints(Player player, Block bl, boolean multiplyRewards, boolean hasTelekinesis) {
        if(!isActive()) return;
        if(plugin == null) return;
        byte data = bl.getData();
        String key = bl.getType().toString();
        if (this.plugin.getSkillPoints().skillBlocksXP.containsKey(key)) {
            BlockXP skillXP = (BlockXP)this.plugin.getSkillPoints().skillBlocksXP.get(key);
            if (data == skillXP.getMaterialData() || skillXP.getMaterialData() == -1) {
                SkillType skillType = skillXP.getSkillType();
                Skill skill = (Skill)this.plugin.getSkills().getAllSkills().get(skillType);
                if (skill.isEnabled()) {
                    if (!player.getGameMode().equals(GameMode.CREATIVE) || skill.isXpInCreative()) {
                        if (!Utils.isInBlockedWorld(bl.getWorld().getName(), skillType)) {
                            if (!Utils.isInBlockedRegion(bl.getLocation(), skillType)) {
                                double xp = !key.contains("SUGAR_CANE") && !key.contains("CACTUS") ? skillXP.getXp() : skillXP.getXp() * (double)Utils.getBlockQuantity(bl, bl.getType());
                                if (!(xp <= 0.0D)) {
                                    if (multiplyRewards) {
                                        double percentage = -1.0D;
                                        if (skillType == SkillType.Farming) {
                                            percentage = this.plugin.getApi().getTotalPerk(player.getUniqueId(), Perk.Crop_Chance);
                                        } else if (skillType == SkillType.Mining) {
                                            percentage = this.plugin.getApi().getTotalPerk(player.getUniqueId(), Perk.Ore_Chance);
                                        } else if (skillType == SkillType.Foraging) {
                                            percentage = this.plugin.getApi().getTotalPerk(player.getUniqueId(), Perk.Log_Chance);
                                        }

                                        if (percentage == -1.0D) {
                                            return;
                                        }

                                        if (Utils.hasSkillTouch(player)) {
                                            return;
                                        }

                                        DoubleItemPerks.multiplyRewards(player, skillType, bl, percentage, hasTelekinesis);
                                    }

                                    this.plugin.getSkillManager().addXP(player.getUniqueId(), skillType, xp);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
