package emt.gthandler.common.loader;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import emt.gthandler.common.implementations.EssentiaHatch;
import emt.gthandler.common.items.EMT_CasingBlock;
import emt.gthandler.common.tileentities.machines.multi.generator.EMT_Large_Essentia_Gen;
import emt.tile.GT_MetaTileEntity_ResearchCompleter;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

import static emt.command.CommandOutputs.mkbook;

public class EMT_GT_Loader implements Runnable {


    public static final int TIERS = GT_Values.VN.length;
    public static int aIDoffset = 13000 - TIERS - 1;
    public static ItemStack[] EHatch = new ItemStack[TIERS];
    public static ItemStack LEG;
    public static ItemStack ResearchMultiblock;

    @Override
    public void run() {

        for (int i = 0; i < TIERS; i++) {
            EHatch[i] = new EssentiaHatch(aIDoffset + i, "Essentia Hatch " + GT_Values.VN[i], "Essentia Hatch " + GT_Values.VN[i], i).getStackForm(1L);
        }
        GameRegistry.registerBlock(EMT_CasingBlock.EMT_GT_BLOCKS[0], EMT_CasingBlock.class, "EMT_GTBLOCK_CASEING");
        LEG = new EMT_Large_Essentia_Gen(aIDoffset + TIERS + 1, "Large Essentia Generator", "Large Essentia Generator").getStackForm(1L);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Indium, 1L), GT_OreDictUnificator.get(OrePrefixes.stickLong, Materials.Tungsten, 4L)}, Materials.SolderingAlloyMK1.getMolten(576L), new ItemStack(EMT_CasingBlock.EMT_GT_BLOCKS[0], 1, 3), 100, (int) (GT_Values.V[5] - (GT_Values.V[5] / 10)));
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{new ItemStack(EMT_CasingBlock.EMT_GT_BLOCKS[0], 1, 3), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iridium, 8L)}, Materials.Osmiridium.getMolten(576L), new ItemStack(EMT_CasingBlock.EMT_GT_BLOCKS[0], 1, 5), 100, (int) (GT_Values.V[5] - (GT_Values.V[5] / 10)));
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Iridium, 1L), GT_OreDictUnificator.get(OrePrefixes.stickLong, Materials.Osmiridium, 4L)}, Materials.SolderingAlloyMK1.getMolten(576L), new ItemStack(EMT_CasingBlock.EMT_GT_BLOCKS[0], 1, 2), 100, (int) (GT_Values.V[5] - (GT_Values.V[5] / 10)));
        if (Loader.isModLoaded("ThaumicTinkerer"))
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{new ItemStack(EMT_CasingBlock.EMT_GT_BLOCKS[0], 1, 2), GT_ModHandler.getModItem("ThaumicTinkerer", "kamiResource", 2L, 2)}, Materials.Concrete.getMolten(2304L), new ItemStack(EMT_CasingBlock.EMT_GT_BLOCKS[0], 1, 4), 100, (int) (GT_Values.V[5] - (GT_Values.V[5] / 10)));
        else
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{new ItemStack(EMT_CasingBlock.EMT_GT_BLOCKS[0], 1, 2), Materials.ElectrumFlux.getPlates(8)}, Materials.Concrete.getMolten(2304L), new ItemStack(EMT_CasingBlock.EMT_GT_BLOCKS[0], 1, 4), 100, (int) (GT_Values.V[5] - (GT_Values.V[5] / 10)));

        for (int i = 0; i < ItemList.HATCHES_INPUT.length; i++)
            GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.HATCHES_INPUT[i].get(1L), new ItemStack(ConfigBlocks.blockTube, 1, 4)}, Materials.Thaumium.getMolten(288L), EHatch[i], 100, (int) (GT_Values.V[i] - (GT_Values.V[i] / 10)));

        ResearchMultiblock = new GT_MetaTileEntity_ResearchCompleter(aIDoffset + TIERS + 2, "Research Completer", "Research Completer").getStackForm(1L);
    }

    public void runlate() {
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{new ItemStack(Items.book), GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Basic, 1L), new ItemStack(ConfigItems.itemShard, 1, OreDictionary.WILDCARD_VALUE)}, GT_Values.NF, mkbook(), 128, 64);
    }
}
