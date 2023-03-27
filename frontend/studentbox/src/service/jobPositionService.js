import axios from "../custom-axios/axios";

const jobPositionService = {

    fetchAllJobPositions: () => axios.get("/job-positions"),

    createJobPosition: (jobPositionCreationModel) => axios.post("/job-positions",{
        "jobPositionCreationModel": jobPositionCreationModel
    }),

    deleteJobPosition: (id) => axios.delete(`/job-positions/${id}`)
}

export default jobPositionService
