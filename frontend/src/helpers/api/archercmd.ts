import type { Archer } from '../../../types/types.ts';
import { ApiHelper } from '@/helpers/api/apihelper.ts';

export class ArcherCmd {
  public static async getAllArchersCmd(): Promise<Array<Archer>> {
    return (await ApiHelper.basicGetRequest('archers')).json();
  }

  public static async createArcherCmd(archer: Archer): Promise<void> {
    await ApiHelper.basicPostRequest('archers', archer)
      .then((response) => {
        if (!response.ok) {
          alert('Failed to create shooter.');
        }
      })
      .catch((error) => {
        console.error('Error creating shooter:', error);
        alert('An error occurred while creating the shooter.');
      });
  }

  public static async deleteArcherCmd(id: string): Promise<boolean> {
    let responseSuccess = false;

    await ApiHelper.basicDeleteRequest('archers/' + id)
      .then((response) => {
        if (response.ok) {
          responseSuccess = true;
        } else {
          alert('Failed to delete shooter, please try again!');
        }
      })
      .catch((error) => {
        console.error('Error deleting shooter:', error);
        alert('An error occurred while deleting the shooter.');
      });

    return responseSuccess;
  }

  public static async updateArcherCmd(archer: Archer): Promise<boolean> {
    let responseSuccess = false;

    await ApiHelper.basicUpdateRequest('archers/' + archer.id, archer)
      .then((response) => {
        if (response.ok) {
          responseSuccess = true;
        } else {
          alert('Failed to update shooter.');
        }
      })
      .catch((error) => {
        console.error('Error updating shooter:', error);
        alert('An error occurred while updating the shooter.');
      });

    return responseSuccess;
  }
}
