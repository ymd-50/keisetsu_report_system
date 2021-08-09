package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import models.Employee;

public class EmployeeConverter {

    public static Employee toModel(EmployeeView ev) {
        return new Employee(
                ev.getId(),
                ev.getName(),
                ev.getSubject() == null
                    ?null
                    :ev.getSubject() == AttributeConst.SUB_HUM.getIntegerValue()
                        ?AttributeConst.SUB_HUM.getIntegerValue()
                        :AttributeConst.SUB_SCI.getIntegerValue(),
                ev.getWorkStyle() == null
                    ?null
                    :ev.getWorkStyle() == AttributeConst.FULL_TIME.getIntegerValue()
                        ?AttributeConst.FULL_TIME.getIntegerValue()
                        :AttributeConst.PART_TIME.getIntegerValue(),
                ev.getMailAddress(),
                ev.getPassword(),
                ev.getDeleteFlag() == null
                    ?null
                    :ev.getDeleteFlag() == AttributeConst.DEL_FALSE.getIntegerValue()
                        ?AttributeConst.DEL_FALSE.getIntegerValue()
                        :AttributeConst.DEL_TRUE.getIntegerValue(),
                ev.getCreatedAt(),
                ev.getUpdatedAt()
                );
    }

    public static EmployeeView toView(Employee e) {
        if(e == null) {
            return null;
        } else {
            return new EmployeeView(
                    e.getId(),
                    e.getName(),
                    e.getSubject() == null
                        ?null
                        :e.getSubject() == AttributeConst.SUB_HUM.getIntegerValue()
                            ?AttributeConst.SUB_HUM.getIntegerValue()
                            :AttributeConst.SUB_SCI.getIntegerValue(),
                    e.getWorkStyle() == null
                        ?null
                        :e.getWorkStyle() == AttributeConst.FULL_TIME.getIntegerValue()
                            ?AttributeConst.FULL_TIME.getIntegerValue()
                            :AttributeConst.PART_TIME.getIntegerValue(),
                    e.getMailAddress(),
                    e.getPassword(),
                    e.getDeleteFlag() == null
                        ?null
                        :e.getDeleteFlag() == AttributeConst.DEL_FALSE.getIntegerValue()
                            ?AttributeConst.DEL_FALSE.getIntegerValue()
                            :AttributeConst.DEL_TRUE.getIntegerValue(),
                    e.getCreatedAt(),
                    e.getUpdatedAt()
                    );
        }

    }

    public static List<EmployeeView> toViewList(List<Employee> list){
        List<EmployeeView> evs = new ArrayList<>();

        for(Employee e : list) {
            evs.add(toView(e));
        }

        return evs;
    }

    public static void copyViewToModel(Employee e, EmployeeView ev) {
        e.setCreatedAt(ev.getCreatedAt());
        e.setDeleteFlag(ev.getDeleteFlag());
        e.setId(ev.getId());
        e.setMailAddress(ev.getMailAddress());
        e.setName(ev.getName());
        e.setPassword(ev.getPassword());
        e.setSubject(ev.getSubject());
        e.setUpdatedAt(ev.getUpdatedAt());
        e.setUpdatedAt(ev.getUpdatedAt());
    }
}
