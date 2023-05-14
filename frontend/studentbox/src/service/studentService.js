import axios from "../custom-axios/axios";

const studentService = {

    fetchAllSkills: () => axios.get("/students/skills"),

    addSkill: (skillId) => axios.post(`/students/skills/${skillId}`),

    deleteSkill: (skillId) => axios.delete(`/students/skills/${skillId}`),

    getEducation: () => axios.get("/students/education"),

    addEducation: (educationCreationModel) => axios.post("/students/education",{
        "educationCreationModel":educationCreationModel
    }),

    editEducation: (educationId,educationCreationModel) => axios.patch(`/students/education/${educationId}`,{
        "educationCreationModel":educationCreationModel
    }),

    deleteEducation: (educationId) => axios.delete(`/students/education/${educationId}`),

    getCertificates: () => axios.get("/students/certificates"),

    addCertificate: (certificateCreationModel) => axios.post("/students/certificates",{
        "certificateCreationModel":certificateCreationModel
    }),

    deleteCertificate: (certificateId) => axios.delete(`/students/certificates/${certificateId}`),

    addEmploymentInfo: (employmentInfoCreationModel) => axios.post("/students/employment-info",{
        "employmentInfoCreationModel":employmentInfoCreationModel
    }),

    deleteEmploymentInfo:(employmentId) => axios.delete(`/students/employment-info/${employmentId}`)




















}
