<script setup lang="ts">
import { ApiHelper } from '@/helpers/api/apihelper.ts';
import { onMounted, ref } from 'vue';
import type { Archer } from '../../../types/types.ts';
import ShooterInfoCard from '@/components/ShooterInfoCard.vue';
import CreateShooterDialog from '@/components/CreateShooterDialog.vue';

const shooters = ref<Array<Archer>>([]);

onMounted(async () => {
  shooters.value = await ApiHelper.fetchShooters();
});

const onDeleteButton = async (id: string) => {
  const confirmed = confirm('Sind Sie sicher, dass Sie diesen Schützen löschen möchten?');

  if (confirmed) {
    const success = await ApiHelper.deleteShooter(id);

    if (success) {
      shooters.value = shooters.value.filter((shooter) => shooter.id !== id);
    } else {
      alert('Fehler beim Löschen des Schützen. Bitte versuchen Sie es erneut.');
    }
  }
};
</script>

<template>
  <div data-testid="shooters-management-page" class="page">
    <div class="shooter-info-cards-wrapper">
      <div v-if="shooters.length == 0" class="shooters-empty-wrapper">
        <h2>Keine Schützen vorhanden</h2>
        <p>Erstellen Sie einen neuen Schützen, indem Sie auf den Button unten rechts klicken.</p>
      </div>

      <div v-for="shooter in shooters" :key="shooter.id" class="shooter-card-wrapper">
        <ShooterInfoCard :shooter="shooter" :onDeleteButton="onDeleteButton" />
      </div>
    </div>

    <div class="shooters-create-new-button-wrapper">
      <CreateShooterDialog />
    </div>
  </div>
</template>

<style scoped>
.shooters-empty-wrapper {
  text-align: center;
  margin-top: 50px;
}

.shooter-info-cards-wrapper {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.shooters-create-new-button-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  position: absolute;
  right: 30px;
  bottom: 30px;
}
</style>
