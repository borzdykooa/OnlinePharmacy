package by.itacademy.service;

import by.itacademy.dto.GroupDto;
import by.itacademy.entity.Group;
import by.itacademy.dao.GroupDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GroupService {

    private static final GroupService INSTANCE = new GroupService();

    public void save(GroupDto dto) {
        Group request = Group.builder()
                .name(dto.getName())
                .build();
        GroupDao.getInstance().save(request);
    }

    public List<Group> findAllGroups() {
        return GroupDao.getInstance().getAllGroups();
    }

    public static GroupService getInstance() {
        return INSTANCE;
    }
}
