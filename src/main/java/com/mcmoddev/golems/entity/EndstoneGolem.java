package com.mcmoddev.golems.entity;

import com.mcmoddev.golems.entity.base.GolemBase;
import com.mcmoddev.golems.events.EndGolemTeleportEvent;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class EndstoneGolem extends GolemBase {

  public static final String ALLOW_SPECIAL = "Allow Special: Teleporting";
  public static final String ALLOW_WATER_HURT = "Can Take Water Damage";

  /**
   * Max distance for one teleport; range is 32.0 for endstone golem, 64 for
   * enderman.
   **/
  protected double range = 32.0D;
  protected boolean allowTeleport = true;
  protected boolean isHurtByWater = true;
  protected boolean hasAmbientParticles = true;

  protected int ticksBetweenIdleTeleports = 200;
  /**
   * Percent chance to teleport away when hurt by non-projectile.
   **/
  protected int chanceToTeleportWhenHurt = 15;

  /** Default constructor. **/
  public EndstoneGolem(final EntityType<? extends GolemBase> entityType, final World world) {
    this(entityType, world, 32.0D, true);
    this.isHurtByWater = this.getConfigBool(ALLOW_WATER_HURT);
    this.allowTeleport = this.getConfigBool(ALLOW_SPECIAL);
    this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
    if (isHurtByWater) {
      this.setPathPriority(PathNodeType.WATER, -1.0F);
    }
  }

  /**
   * Flexible constructor to allow child classes to customize.
   *
   * @param entityType       the EntityType
   * @param world            the worldObj
   * @param teleportRange    64.0 for enderman, 32.0 for endstone golem
   * @param ambientParticles whether to always display "portal" particles
   **/
  public EndstoneGolem(final EntityType<? extends GolemBase> entityType, final World world, final double teleportRange,
      final boolean ambientParticles) {
    super(entityType, world);
    this.range = teleportRange;
    this.hasAmbientParticles = ambientParticles;
  }

  @Override
  public void updateAITasks() {
    super.updateAITasks();
    // try to teleport toward target entity
    if (this.getRevengeTarget() != null) {
      this.faceEntity(this.getRevengeTarget(), 100.0F, 100.0F);
      if (this.getRevengeTarget().getDistanceSq(this) > 25.0D && (rand.nextInt(30) == 0 || this.getRevengeTarget().getRevengeTarget() == this)) {
        this.teleportToEntity(this.getRevengeTarget());
      }
    } else if (rand.nextInt(this.ticksBetweenIdleTeleports) == 0) {
      // or just teleport randomly
      this.teleportRandomly();
    }

  }

  @Override
  public void livingTick() {
    if (this.world.isRemote && this.hasAmbientParticles) {
      final Vec3d pos = this.getPositionVec();
      for (int i = 0; i < 2; ++i) {
        this.world.addParticle(ParticleTypes.PORTAL, pos.x + (this.rand.nextDouble() - 0.5D) * (double) this.getWidth(),
            pos.y + this.rand.nextDouble() * (double) this.getHeight() - 0.25D, pos.z + (this.rand.nextDouble() - 0.5D) * (double) this.getWidth(),
            (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
      }
    }

    this.isJumping = false;
    super.livingTick();
  }

  @Override
  public boolean attackEntityFrom(final DamageSource src, final float amnt) {
    if (this.isInvulnerableTo(src)) {
      return false;
    }

    // if it's an arrow or something...
    if (src instanceof IndirectEntityDamageSource) {
      // try to teleport to the attacker
      if (src.getTrueSource() instanceof LivingEntity && this.teleportToEntity(src.getTrueSource())) {
        this.setRevengeTarget((LivingEntity) src.getTrueSource());
        return super.attackEntityFrom(src, amnt);
      }
      // if teleporting to the attacker didn't work, golem teleports AWAY
      for (int i = 0; i < 32; ++i) {
        if (this.teleportRandomly()) {
          return false;
        }
      }
    } else {
      // if it's something else, golem MIGHT teleport away
      // if it passes a random chance OR has no attack target
      if (rand.nextInt(this.chanceToTeleportWhenHurt) == 0 || (this.getRevengeTarget() == null && rand.nextBoolean())
          || (this.isHurtByWater && src == DamageSource.DROWN)) {
        // attempt teleport
        for (int i = 0; i < 16; ++i) {
          if (this.teleportRandomly()) {
            break;
          }
        }
      }
    }
    return super.attackEntityFrom(src, amnt);
  }

  protected boolean teleportRandomly() {
    final Vec3d pos = this.getPositionVec();
    final double d0 = pos.x + (this.rand.nextDouble() - 0.5D) * range;
    final double d1 = pos.y + (this.rand.nextDouble() - 0.5D) * range * 0.5D;
    final double d2 = pos.z + (this.rand.nextDouble() - 0.5D) * range;
    return this.teleportTo(d0, d1, d2);
  }

  /**
   * Teleport the golem to another entity.
   **/
  protected boolean teleportToEntity(final Entity entity) {
    final Vec3d curPos = this.getPositionVec();
    final Vec3d ePos = entity.getPositionVec();
    Vec3d vec3d = new Vec3d(curPos.x - ePos.x,
        this.getBoundingBox().minY + (double) (this.getHeight() / 2.0F) - ePos.y + (double) entity.getEyeHeight(), curPos.z - ePos.z);
    vec3d = vec3d.normalize();
    final double d0 = 16.0D;
    final double d1 = curPos.x + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * d0;
    final double d2 = curPos.y + (double) (this.rand.nextInt(16) - 8) - vec3d.y * d0;
    final double d3 = curPos.z + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * d0;
    return this.teleportTo(d1, d2, d3);
  }

  /**
   * Teleport the golem.
   **/
  protected boolean teleportTo(final double x, final double y, final double z) {
    final EndGolemTeleportEvent event = new EndGolemTeleportEvent(this, x, y, z, 0);
    if (!this.allowTeleport || MinecraftForge.EVENT_BUS.post(event)) {
      return false;
    }
    final boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);

    if (flag) {
      this.world.playSound((PlayerEntity) null, this.prevPosX, this.prevPosY, this.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT,
          this.getSoundCategory(), 1.0F, 1.0F);
      this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
    }

    return flag;
  }
}
