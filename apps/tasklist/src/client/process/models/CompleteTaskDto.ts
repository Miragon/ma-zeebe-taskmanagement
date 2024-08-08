import { UserTaskDto } from "../../generated/taskmanager";

export interface CompleteTaskDto {
    userTask: UserTaskDto;
    formData: any;
}
