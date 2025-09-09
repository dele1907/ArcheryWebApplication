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
}
