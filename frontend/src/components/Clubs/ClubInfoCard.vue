<script setup lang="ts">
import type { Club } from '../../../types/types.ts';
import EditClubInformationDialog from '@/components/Clubs/EditClubInformationDialog.vue';

const props = defineProps<{
  club: Club;
  onDeleteButton: (id: string) => Promise<void>;
  onClubUpdated: (updatedClub: Club) => Promise<void>;
}>();
</script>

<template>
  <div class="club-info-card-wrapper">
    <v-card width="500" height="300" class="ma-4 club-info-card">
      <v-card-text class="text-center ma-16">
        <h2 class="ma-4">{{ props.club.clubName }}</h2>
        <h3 class="ma-3">Vereinsnummer: {{ props.club.clubId }}</h3>
      </v-card-text>
      <v-btn
        size="small"
        icon="fa-trash"
        class="ma-1 club-delete-button"
        color="primary"
        @click="onDeleteButton(props.club.clubId)"
      ></v-btn>
      <EditClubInformationDialog
        class="club-edit-dialog"
        :club="club"
        :onClubUpdated="props.onClubUpdated"
      />
    </v-card>
  </div>
</template>

<style scoped>
.club-info-card {
  justify-content: center;
  .club-delete-button {
    position: absolute;
    bottom: 10px;
    right: 60px;
  }
  .club-edit-dialog {
    position: absolute;
    bottom: 10px;
    right: 10px;
  }
}
</style>
