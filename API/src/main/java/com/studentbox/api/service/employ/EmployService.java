package com.studentbox.api.service.employ;

import com.studentbox.api.entities.company.Company;
import com.studentbox.api.entities.company.job.JobOffer;
import com.studentbox.api.entities.student.Student;

import java.util.List;

public interface EmployService {

    /*
      Да ги пребара сите JobOffers и да ги врати сортирани според процент на совпаѓања на skills меѓу JobPosition и Student.
      Минимум 50% совпаѓања на Skills
      Мислам дека фали табела за поврзување на Skills и JobPosition.

      Мозе и внатре да се земе кој е најавен.

      Дополнително, employmentInfo мозе да носе бодови ако била за дадената позиција или позиција со слични вештини.
     */

    List<JobOffer> offers (Student student);


    /*
     На дадена компанија, да им ги врате  студентите според 50% совпаѓа на вештините и за даден работен оглас.

     Подобро е да се враќа комбинација од даден работен оглас и студент.

     Дополнително, employmentInfo мозе да носе бодови ако била за дадената позиција или позиција со слични вештини.

     */
    List<Student> students (Company company);



    /*
     1. За даден работен оглас што ќе му се појави на студент, истиот избира дали ќе одбере или не.
     2. За даден студент на работен оглас, компанијата, одбира дали ќе го прифати или не.
     За да има match, потребно е двојно одобрување.
     Мислам дека и тука фале табела составена од Студент, Работен оглас и две булови променливи (една за студент
     прифатил или не, другата за компанијата).

     наместо да се праќаат како параметри, мозе внатре да се извадата согласно кој е најавен.
     */
    boolean matchJob (Student user, JobOffer jobOffer);

    void skipJob (Student user, JobOffer jobOffer);

}
