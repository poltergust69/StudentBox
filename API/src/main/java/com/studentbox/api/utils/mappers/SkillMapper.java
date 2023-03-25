package com.studentbox.api.utils.mappers;

import com.studentbox.api.entities.skill.Skill;
import com.studentbox.api.models.skill.SkillModel;

import java.util.List;

import static com.studentbox.api.utils.containers.ExceptionMessageContainer.UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE;

public class SkillMapper {
    private SkillMapper() {
        throw new IllegalStateException(UTILITY_CLASS_INITIALIZED_EXCEPTION_MESSAGE);
    }

    public static SkillModel mapToModel(Skill skill){
        return new SkillModel(skill);
    }

    public static List<SkillModel> mapAllToModel(List<Skill> skills){
        return skills
                .stream()
                .map(SkillMapper::mapToModel)
                .toList();
    }
}
