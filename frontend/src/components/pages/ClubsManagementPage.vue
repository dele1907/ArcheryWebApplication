<script setup lang="ts">
import { onMounted, ref } from 'vue';
import type { Club } from '../../../types/types.ts';
import ClubInfoCard from '@/components/Clubs/ClubInfoCard.vue';
import { ClubCmd } from '@/helpers/api/clubcmd.ts';
import CreateClubDialog from '@/components/Clubs/CreateClubDialog.vue';

const clubs = ref<Array<Club>>([]);

onMounted(async () => {
  clubs.value = await ClubCmd.getAllClubsCmd();
});

const onDeleteButton = async (id: string) => {
  const confirmed = confirm('Sind Sie sicher, dass Sie diesen Verein löschen möchten?');

  if (confirmed) {
    const success = await ClubCmd.deleteClubCmd(id);

    if (success) {
      clubs.value = clubs.value.filter((club) => club.clubId !== id);
    } else {
      alert('Fehler beim Löschen des Vereins. Bitte versuchen Sie es erneut.');
    }
  }
};

const onClubUpdated = async (updatedClub: Club) => {
  const response = await ClubCmd.updateClubCmd(updatedClub);

  if (response) {
    clubs.value = await ClubCmd.getAllClubsCmd();
  } else {
    alert('Fehler beim Aktualisieren des Vereins. Bitte versuchen Sie es erneut.');
  }
};

const onCreateClub = async (newClub: Club) => {
  await ClubCmd.createClubCmd(newClub);

  clubs.value = await ClubCmd.getAllClubsCmd();
};
</script>

<template>
  <div data-testid="clubs-management-page" class="page">
    <div class="club-info-cards-wrapper">
      <div v-if="clubs.length == 0" class="clubs-empty-wrapper">
        <h2>Keine Vereine vorhanden</h2>
        <p>Legen Sie einen neuen Verein an, indem Sie auf den Button unten rechts klicken.</p>
      </div>

      <div v-for="club in clubs" :key="club.clubId" class="club-card-wrapper">
        <ClubInfoCard
          :club="club"
          :onDeleteButton="onDeleteButton"
          :onClubUpdated="onClubUpdated"
        />
      </div>
    </div>

    <div class="clubs-create-new-button-wrapper">
      <CreateClubDialog :on-create-club="onCreateClub" />
    </div>
  </div>
</template>

<style scoped>
.clubs-empty-wrapper {
  text-align: center;
  margin-top: 50px;
}

.club-info-cards-wrapper {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.clubs-create-new-button-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  position: absolute;
  right: 30px;
  bottom: 30px;
}
</style>
