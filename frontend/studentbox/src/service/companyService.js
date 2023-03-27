import axios from "../custom-axios/axios";

const companyService = {

    fetchAllJobs: () => axios.get("/companies/job-posts"),

    addJob: (company, jobPosition, salary, description) => axios.post("/companies/job-posts",{
        "company":company,
        "jobPosition":jobPosition,
        "salary": salary,
        "description":description
    }),

    deleteJob: (id) => axios.delete("/companies/job-posts/{id}"),

    register: (registerCompanyDetails) => axios.post("/companies/register",{
        "registerCompanyDetails": registerCompanyDetails
    })

}

export default companyService
