import type { Club } from '../../../types/types.ts';
import { ApiHelper } from '@/helpers/api/apihelper.ts';

export class ClubCmd {
  public static async getAllClubsCmd(): Promise<Array<Club>> {
    return (await ApiHelper.basicGetRequest('clubs')).json();
  }

  public static async updateClubCmd(club: Club): Promise<boolean> {
    let responseSuccess = false;

    await ApiHelper.basicUpdateRequest('clubs/' + club.clubId, club)
      .then((response) => {
        if (response.ok) {
          responseSuccess = true;
        } else {
          alert('Failed to update club.');
        }
      })
      .catch((error) => {
        console.error('Error updating club:', error);
        alert('An error occurred while updating the club.');
      });

    return responseSuccess;
  }

  public static async createClubCmd(club: Club): Promise<void> {
    await ApiHelper.basicPostRequest('clubs', club)
      .then((response) => {
        if (!response.ok) {
          alert('Failed to create club.');
        }
      })
      .catch((error) => {
        console.error('Error creating club:', error);
        alert('An error occurred while creating the club.');
      });
  }

  public static async deleteClubCmd(id: string): Promise<boolean> {
    let responseSuccess = false;

    await ApiHelper.basicDeleteRequest('clubs/' + id)
      .then((response) => {
        if (response.ok) {
          responseSuccess = true;
        } else {
          alert('Failed to delete club, please try again!');
        }
      })
      .catch((error) => {
        console.error('Error deleting club:', error);
        alert('An error occurred while deleting the club.');
      });

    return responseSuccess;
  }
}
