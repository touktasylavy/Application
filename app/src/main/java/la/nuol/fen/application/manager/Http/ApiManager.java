package la.nuol.fen.application.manager.Http;

import la.nuol.fen.application.dao.Buildings;
import la.nuol.fen.application.dao.Classrooms;
import la.nuol.fen.application.dao.Departments;
import la.nuol.fen.application.dao.Divisions;
import la.nuol.fen.application.dao.LabRooms;
import la.nuol.fen.application.dao.LibraryRooms;
import la.nuol.fen.application.dao.Locations;
import la.nuol.fen.application.dao.MeetingRooms;
import la.nuol.fen.application.dao.News;
import la.nuol.fen.application.dao.Office;
import la.nuol.fen.application.dao.ProgramBachelor;
import la.nuol.fen.application.dao.ProgramContinue;
import la.nuol.fen.application.dao.ProgramMaster;
import la.nuol.fen.application.utils.ContactUrls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface ApiManager {

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.LOCATION)
    Call<Locations> getLocation();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.BUILDING)
    Call<Buildings> getBuilding();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.PROGRAM_CONTINUE)
    Call<ProgramContinue> getProgramContinue();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.PROGRAM_BACHELOR)
    Call<ProgramBachelor> getProgramBachelor();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.PROGRAM_MASTER)
    Call<ProgramMaster> getProgramMaster();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.DEPARTMENT)
    Call<Departments> getDepartment();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.DIVISION)
    Call<Divisions> getDivision();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.CLASSROOM)
    Call<Classrooms> getClassroom();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.OFFICE)
    Call<Office> getOffice();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.MEETING_ROOM)
    Call<MeetingRooms> getMeetingRoom();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.LIBRARY_ROOM)
    Call<LibraryRooms> getLibraryRoom();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.LAB_ROOM)
    Call<LabRooms> getLabRoom();

    @Headers({"Content-Type: application/json"})
    @GET(ContactUrls.NEWS)
    Call<News> getNews();

}
