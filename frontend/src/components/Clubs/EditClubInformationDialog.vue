<script setup lang="ts">
import type { Club } from '../../../types/types.ts';
import { ref, shallowRef } from 'vue';

const props = defineProps<{
  club: Club;
  onClubUpdated: (updatedClub: Club) => Promise<void>;
}>();

const initialCLub = {
  clubId: props.club.clubId,
  clubName: props.club.clubName,
};

const editedClub = ref<Club>(initialCLub);

const dialog = shallowRef(false);

const onCancelButton = () => {
  dialog.value = false;
};
</script>

<template>
  <div class="club-edit-dialog-wrapper">
    <v-dialog v-model="dialog" max-width="600">
      <template v-slot:activator="{ props: activatorProps }">
        <v-btn
          class="ma-1"
          size="small"
          icon="fa-pencil"
          color="primary"
          v-bind="activatorProps"
        ></v-btn>
      </template>

      <v-card prepend-icon="fa-users" title="Verein bearbeiten">
        <v-card-text>
          <v-row dense>
            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Vereinsnummer *"
                v-model="editedClub.clubId"
                required
                autofocus
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Vereinsname *"
                v-model="editedClub.clubName"
                persistent-hint
                required
              ></v-text-field>
            </v-col>
          </v-row>

          <small class="text-caption text-medium-emphasis">* Pflichtfelder</small>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn text="Abbrechen" variant="plain" @click="onCancelButton"></v-btn>

          <v-btn
            color="primary"
            icon="fa-save"
            @click="
              props.onClubUpdated(editedClub);
              dialog = false;
            "
          ></v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<style scoped></style>
