package com.sit.cloudnative.SubjectService.Subject;

import com.sit.cloudnative.SubjectService.Curriculum.Curriculum;
import com.sit.cloudnative.SubjectService.Curriculum.CurriculumService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectAdapter subjectAdapter;

    @Autowired
    private CurriculumService curriculumService;

    public SubjectService(CurriculumService curriculumService, SubjectAdapter subjectAdapter) {
        this.curriculumService = curriculumService;
        this.subjectAdapter = subjectAdapter;
	}

	public List<Subject> getSubjectListByCurriculumId(long curriculumId) {
        List<Subject> subjectList = subjectAdapter.getSubjectListByCurriculumId(curriculumId);
        return subjectList;
    }

    public Subject getSubjectById(long subjectId) {
        Subject subject = subjectAdapter.getSubjectBySubjectId(subjectId);
        return subject;
    }

    public List<Subject> searchSubject(String keyword) {
        List<Curriculum> curriculums = curriculumService.getAllCurriculum();
        List<Subject> subjects = new ArrayList<>();
        List<Subject> keywordSubject = new ArrayList<>();
        for (Curriculum curriculum : curriculums) {
            subjects.addAll(subjectAdapter.getSubjectListByCurriculumId(curriculum.getCurriculumId()));
        }
        for (Subject subject : subjects) {
            if (subject.getSubjectCode().toLowerCase().contains(keyword) || subject.getSubjectName().toLowerCase().contains(keyword)) {
                keywordSubject.add(subject);
            }
        }

        return keywordSubject;
    }

}
