import type { Archer } from '../../../types/types.ts'

export class ApiHelper {
  private static apiUrl = 'http://localhost:7070/'

  public static async fetchShooters(): Promise<Array<Archer>> {
    return fetch(this.apiUrl + 'archers').then((response) => response.json())
  }
}
