import type { Archer } from '../../../types/types.ts'

export class ApiHelper {
  private static apiUrl = 'http://localhost:7070/'

  public static async fetchShooters(): Promise<Array<Archer>> {
    return fetch(this.apiUrl + 'archers').then((response) => response.json())
  }

  public static async createShooter(shooter: Archer): Promise<Response> {
    return fetch(this.apiUrl + 'archers', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(shooter),
    }).then((response) => response)
  }

  public static async deleteShooter(id: string): Promise<Response> {
    return fetch(this.apiUrl + 'archers/' + id, {
      method: 'DELETE',
    }).then((response) => response)
  }
}
