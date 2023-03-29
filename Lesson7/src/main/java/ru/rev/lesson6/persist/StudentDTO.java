package ru.rev.lesson6.persist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    private Long id;
    private String name;
    private String mark;

}
