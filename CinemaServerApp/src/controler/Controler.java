/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import domain.GeneralEntity;
import so.member.DeleteMemberSO;
import so.member.GetMembersSO;
import so.member.SaveMemberSO;
import so.member.UpdateMemberSO;
import so.user.LoginSO;
import transfer.Response;
import forms.ServerForm;
import so.auditoriums.GetAuditoriumsSO;
import so.movies.GetMoviesSO;
import so.projections.DeleteProjectionsSO;
import so.projections.GetProjectionsSO;
import so.projections.SaveProjectionSO;
import so.projections.UpdateProjectionSO;
import so.reservations.DeleteReservationSO;
import so.reservations.GetReservationsSO;
import so.reservations.SaveReservationSO;
import so.reservations.UpdateReservationSO;

/**
 *
 * @author Monika
 */
public class Controler {

    private static Controler instance;
    ServerForm sf;

    private Controler() {
    }

    public static Controler getInstance() {
        if (instance == null) {
            instance = new Controler();
        }
        return instance;
    }

    public void setSf(ServerForm sf) {
        this.sf = sf;
    }

    public ServerForm getSf() {
        return sf;
    }

    public Response login(GeneralEntity ge) {
        return new LoginSO().executeTransaction(ge);
    }
      
    public Response saveMember(GeneralEntity ge) {
        return new SaveMemberSO().executeTransaction(ge);
    }

    public Response getMembers(GeneralEntity ge) {
        return new GetMembersSO().executeTransaction(ge);
    }

    public Response updateMember(GeneralEntity ge) {
        return new UpdateMemberSO().executeTransaction(ge);
    }

    public Response deleteMember(GeneralEntity ge) {
        return new DeleteMemberSO().executeTransaction(ge);
    }

    public Response saveProjection(GeneralEntity ge) {
        return new SaveProjectionSO().executeTransaction(ge);
    }
     
    public Response getProjections(GeneralEntity ge) {
        return new GetProjectionsSO().executeTransaction(ge);
    }

    public Response updateProjection(GeneralEntity ge) {
        return new UpdateProjectionSO().executeTransaction(ge);
    }
    
    public Response deleteProjection(GeneralEntity ge) {
        return new DeleteProjectionsSO().executeTransaction(ge);
    }

    public Response getMovies(GeneralEntity ge) {
        return new GetMoviesSO().executeTransaction(ge);
    }

    public Response getAuditoriums(GeneralEntity ge) {
        return new GetAuditoriumsSO().executeTransaction(ge);
    }
    
    public Response saveReservation(GeneralEntity ge) {
        return new SaveReservationSO().executeTransaction(ge);
    }

    public Response getReservations(GeneralEntity ge) {
        return new GetReservationsSO().executeTransaction(ge);
    }

    public Response updateReservation(GeneralEntity ge) {
        return new UpdateReservationSO().executeTransaction(ge);
    }

    public Response deleteReservation(GeneralEntity ge) {
        return new DeleteReservationSO().executeTransaction(ge);
    }

}
