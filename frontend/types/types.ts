export type Archer = {
  name: string,
  firstName: string,
  clubId: string,
  clubName: string,
  id: string,
  bowType: string,
  birthDate: string,
  ageCategory: string,
  passportNumber: number,
}

export type Club = {
  clubId: string,
  clubName: string,
}

export type ArcherDTO = {
  name: string,
  firstName: string,
  clubId: number,
  bowType: string,
  passportNumber: number,
  birthDate: string,
}
