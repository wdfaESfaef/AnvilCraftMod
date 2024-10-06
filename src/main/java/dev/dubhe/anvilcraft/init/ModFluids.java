package dev.dubhe.anvilcraft.init;

import dev.dubhe.anvilcraft.AnvilCraft;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@SuppressWarnings("removal")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ModFluids {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, AnvilCraft.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, AnvilCraft.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> OIL_TYPE = FLUID_TYPES.register(
        "oil",
        () -> new FluidType(FluidType.Properties.create()
            .density(2000)
            .viscosity(4000)
            .fallDistanceModifier(0F)
            .motionScale(0.007)
            .supportsBoating(true)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
            .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
        ) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public ResourceLocation getStillTexture() {
                        return AnvilCraft.of("block/oil");
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return AnvilCraft.of("block/oil");
                    }
                });
            }
        }
    );
    public static final DeferredHolder<Fluid, BaseFlowingFluid> OIL = FLUIDS
        .register(
            "oil",
            () -> new BaseFlowingFluid.Source(ModFluids.OIL_PROPERTIES)
        );

    public static final DeferredHolder<Fluid, BaseFlowingFluid> FLOWING_OIL = FLUIDS
        .register(
            "flowing_oil",
            () -> new BaseFlowingFluid.Flowing(ModFluids.OIL_PROPERTIES)
        );
    public static final BaseFlowingFluid.Properties OIL_PROPERTIES = new BaseFlowingFluid.Properties(OIL_TYPE, OIL, FLOWING_OIL)
        .bucket(ModItems.OIL_BUCKET).block(ModBlocks.OIL).tickRate(10).slopeFindDistance(3).explosionResistance(100);

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }
}
