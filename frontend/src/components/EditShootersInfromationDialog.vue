<script setup lang="ts">
import { ref, shallowRef } from 'vue';
import type { Archer } from '../../types/types.ts';
import { DSB_RULESET_HELPER } from '@/helpers/dsbrulesethelper.ts';

const props = defineProps<{
  archer: Archer;
  onShooterUpdated: (updatedArcher: Archer) => Promise<void>;
}>();

const initialArcher = {
  id: props.archer.id,
  firstName: props.archer.firstName,
  name: props.archer.name,
  clubNumber: props.archer.clubNumber,
  passportNumber: props.archer.passportNumber,
  ageCategory: props.archer.ageCategory,
  bowType: props.archer.bowType,
  club: props.archer.club,
};

const editedArcher = ref<Archer>(initialArcher);

const dialog = shallowRef(false);
//TODO make the form functional and prefill with existing data

const onCancelButton = () => {
  dialog.value = false;
};
</script>

<template>
  <div class="shooter-edit-dialog-wrapper">
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

      <v-card prepend-icon="fa-user" title="Neuen Schützen anlegen">
        <v-card-text>
          <v-row dense>
            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Vorname *"
                v-model="editedArcher.firstName"
                required
                autofocus
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Name *"
                v-model="editedArcher.name"
                persistent-hint
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6" sm="6">
              <v-number-input
                label="Vereinsnummer *"
                v-model="editedArcher.clubNumber"
                type="number"
                controlVariant="hidden"
                required
              ></v-number-input>
            </v-col>

            <v-col cols="12" md="6" sm="6">
              <v-number-input
                label="Passnummer *"
                v-model="editedArcher.passportNumber"
                type="number"
                controlVariant="hidden"
                required
              ></v-number-input>
            </v-col>

            <v-col cols="12" md="12" sm="6">
              <v-text-field label="Geburtsadatum *" type="date" required></v-text-field>
            </v-col>

            <v-col cols="12" sm="12">
              <v-select
                :items="DSB_RULESET_HELPER.BOW_TYPES"
                v-model="editedArcher.bowType"
                label="Bogenart *"
                variant="underlined"
                auto-select-first
              ></v-select>
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
            @click="props.onShooterUpdated(editedArcher)"
          ></v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<style scoped></style>
