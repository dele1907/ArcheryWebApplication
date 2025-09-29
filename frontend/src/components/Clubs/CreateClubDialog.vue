<script setup lang="ts">
import type { Club } from '../../../types/types.ts';
import { ref, shallowRef } from 'vue';

const props = defineProps<{
  onCreateClub: (newClub: Club) => Promise<void>;
}>();

const dialog = shallowRef(false);

const newClub: Club = {
  clubId: '',
  clubName: '',
};

const club = ref<Club>(newClub);

function resetFormInputs(): void {
  club.value = {
    clubId: '',
    clubName: '',
  };
}

const necessaryInputFields = ['clubId', 'clubName'];

function getInputFieldsAreValid(): boolean {
  if (necessaryInputFields.some((field) => field.trim() === '')) {
    alert('Please fill all required fields.');

    return false;
  }

  return true;
}

const onSaveButton = async () => {
  if (!getInputFieldsAreValid()) {
    alert('Fill all required fields.');

    return;
  }

  await props.onCreateClub(getClubApiObject());

  resetFormInputs();
  dialog.value = false;
};

function getClubApiObject(): Club {
  return {
    clubId: club.value.clubId,
    clubName: club.value.clubName,
  };
}

const onCancelButton = () => {
  resetFormInputs();
  dialog.value = false;
};
</script>

<template>
  <div class="club-create-dialog-wrapper">
    <v-dialog v-model="dialog" max-width="600">
      <template v-slot:activator="{ props: activatorProps }">
        <v-btn
          class="ma-1"
          size="small"
          icon="fa-plus"
          color="primary"
          v-bind="activatorProps"
        ></v-btn>
      </template>

      <v-card prepend-icon="fa-users" title="Neuen Schützen anlegen">
        <v-card-text>
          <v-row dense>
            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Vereinsnummer *"
                v-model="club.clubId"
                required
                autofocus
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Vereinsname *"
                v-model="club.clubName"
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

          <v-btn color="primary" icon="fa-save" @click="onSaveButton"></v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<style scoped></style>
