package net.lixir.vminus.entity.defaultium;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class DefaultiumEntity extends HostileEntity {
    public DefaultiumEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    public static EntityDimensions getDimensions() {return EntityDimensions.fixed(1f, 1f);}

    
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1D));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {}

    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.BLOCK_ANVIL_DESTROY;
    }

    
    protected void updateLimbs(float posDelta) {}
}
