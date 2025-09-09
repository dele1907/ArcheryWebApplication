import type { Archer } from '../../../types/types.ts';

export class ApiHelper {
  private static API_URL = 'http://localhost:7070/';

  public static getApiUrl(): string {
    return this.API_URL;
  }

  public static async basicGetRequest(endpoint: string): Promise<Response> {
    return fetch(this.API_URL + endpoint).then((response) => response);
  }

  public static async basicPostRequest(endpoint: string, body: object): Promise<Response> {
    return fetch(this.API_URL + endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    }).then((response) => response);
  }

  public static async basicDeleteRequest(endpoint: string): Promise<Response> {
    return fetch(this.API_URL + endpoint, {
      method: 'DELETE',
    }).then((response) => response);
  }

  public static async basicUpdateRequest(endpoint: string, body: object): Promise<Response> {
    return fetch(this.API_URL + endpoint, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(body),
    }).then((response) => response);
  }

  public static async fetchShooters(): Promise<Array<Archer>> {
    return fetch(this.API_URL + 'archers').then((response) => response.json());
  }

  public static async createShooter(shooter: Archer): Promise<Response> {
    return fetch(this.API_URL + 'archers', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(shooter),
    }).then((response) => response);
  }

  public static async deleteShooter(id: string): Promise<Response> {
    return fetch(this.API_URL + 'archers/' + id, {
      method: 'DELETE',
    }).then((response) => response);
  }
}
