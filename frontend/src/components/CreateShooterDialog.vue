<script setup lang="ts">
import { ref, shallowRef } from 'vue';
import type { Archer } from '../../types/types.ts';
import { DSB_RULESET_HELPER } from '@/helpers/dsbrulesethelper.ts';

const props = defineProps<{
  onCreateShooter: (newArcher: Archer) => Promise<void>;
}>();

const dialog = shallowRef(false);

const newArcher: Archer = {
  id: '',
  firstName: '',
  name: '',
  clubId: '',
  passportNumber: 0,
  birthDate: '',
  ageCategory: '',
  bowType: '',
  clubName: '',
};

const archer = ref<Archer>(newArcher);

function resetFormInputs(): void {
  archer.value = {
    id: '',
    firstName: '',
    name: '',
    clubId: '0',
    passportNumber: 0,
    birthDate: '',
    ageCategory: '',
    bowType: '',
    clubName: '',
  };
}

function getNecessaryTextFields(): string[] {
  return [archer.value.firstName, archer.value.name, archer.value.clubId, archer.value.bowType];
}

function getNecessaryNumberFields(): number[] {
  return [archer.value.passportNumber];
}
function getInputFieldsAreValid(): boolean {
  if (getNecessaryTextFields().some((field) => field.trim() === '')) {
    alert('Please fill all required text fields.');

    return false;
  }

  if (getNecessaryNumberFields().some((field) => field === 0 || isNaN(field))) {
    alert('Please fill all required number fields with valid numbers.');

    return false;
  }

  return true;
}

const onSaveButton = async () => {
  if (!getInputFieldsAreValid()) {
    alert('Fill all required fields!');

    return;
  }

  await props.onCreateShooter(getArcherApiObject());

  resetFormInputs();

  dialog.value = false;
};

function getArcherApiObject(): Archer {
  return {
    id: '',
    firstName: archer.value.firstName,
    name: archer.value.name,
    clubId: archer.value.clubId,
    passportNumber: archer.value.passportNumber,
    birthDate: archer.value.birthDate,
    ageCategory: 'Altersklasse',
    bowType: archer.value.bowType,
    clubName: 'Beispiel',
  };
}

const onCancelButton = () => {
  resetFormInputs();
  dialog.value = false;
};
</script>

<template>
  <div class="shooter-create-dialog-wrapper">
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

      <v-card prepend-icon="fa-user" title="Neuen Schützen anlegen">
        <v-card-text>
          <v-row dense>
            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Vorname *"
                v-model="archer.firstName"
                required
                autofocus
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Name *"
                v-model="archer.name"
                persistent-hint
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6" sm="6">
              <v-text-field
                label="Vereinsnummer *"
                v-model="archer.clubId"
                controlVariant="hidden"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" md="6" sm="6">
              <v-number-input
                label="Passnummer *"
                type="number"
                v-model="archer.passportNumber"
                controlVariant="hidden"
                required
              ></v-number-input>
            </v-col>

            <v-col cols="12" md="12" sm="6">
              <v-text-field
                label="Geburtsadatum *"
                type="date"
                v-model="archer.birthDate"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="12" sm="12">
              <v-select
                :items="DSB_RULESET_HELPER.BOW_TYPES"
                label="Bogenart *"
                v-model="archer.bowType"
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

          <v-btn color="primary" icon="fa-save" @click="onSaveButton"></v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<style scoped></style>
