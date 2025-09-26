<script setup lang="ts">
import type { Archer } from '../../types/types.ts';
import EditShootersInfromationDialog from '@/components/EditShootersInfromationDialog.vue';

const props = defineProps<{
  shooter: Archer;
  onDeleteButton: (id: string) => Promise<void>;
  onShooterUpdated: (updatedArcher: Archer) => Promise<void>;
}>();
</script>

<template>
  <div class="shooter-info-card-wrapper">
    <v-card width="500" height="300" class="ma-4 shooter-info-card">
      <v-card-text class="text-center ma-16">
        <h2 class="ma-4">{{ props.shooter.name }}, {{ props.shooter.firstName }}</h2>
        <p><strong>Passnummer:</strong> {{ props.shooter.passportNumber }}</p>
        <p><strong>Vereinsnummer:</strong> {{ props.shooter.clubId }}</p>
        <p><strong>Altersklasse:</strong> {{ props.shooter.ageCategory }}</p>
        <p><strong>Disziplin:</strong> {{ props.shooter.bowType }}</p>
      </v-card-text>
      <v-btn
        size="small"
        icon="fa-trash"
        class="ma-1 shooter-delete-button"
        color="primary"
        @click="onDeleteButton(props.shooter.id)"
      ></v-btn>
      <EditShootersInfromationDialog
        class="shooter-edit-dialog"
        :archer="shooter"
        :onShooterUpdated="props.onShooterUpdated"
      />
    </v-card>
  </div>
</template>

<style scoped>
.shooter-info-card {
  justify-content: center;
  .shooter-delete-button {
    position: absolute;
    bottom: 10px;
    right: 60px;
  }
  .shooter-edit-dialog {
    position: absolute;
    bottom: 10px;
    right: 10px;
  }
}
</style>
