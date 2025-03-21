package org.examples;

import org.examples.myarticle.Person;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyJavaClass {

    private String unknownField;

    @Nullable
    private String nullableField;

    @NotNull
    private String notNullField;

    public MyJavaClass() {
        // Without this-> Warning: Not-null fields must be initialized
        notNullField = "NotNull";
    }

    public MyJavaClass(@NotNull String notNullField) {
        this.notNullField = notNullField;
    }

    public static void main(String[] args) {
        final MyJavaClass myJavaClass = new MyJavaClass();
        // Method invocation 'length' may produce 'NullPointerException'
        System.out.println(myJavaClass.getNullableField().length());

        // Passing 'null' argument to parameter annotated as @NotNull
//        new MyJavaClass(null);


        // Wrong
        String someField1 = MyKotlinClass.Companion.getSOME_FIELD1();

        // Correct
        String someField2 = MyKotlinClass.SOME_FIELD2;


//        MyKotlinClass.forId();

        new Person("", 1).getTestString();

    }

    public String getUnknownField() {
        return unknownField;
    }

    public void setUnknownField(String unknownField) {
        this.unknownField = unknownField;
    }

    @Nullable
    public String getNullableField() {
        return nullableField;
    }

    public void setNullableField(@Nullable String nullableField) {
        this.nullableField = nullableField;
    }

    @NotNull
    public String getNotNullField() {
        return notNullField;
    }

    public void setNotNullField(@NotNull String notNullField) {
        this.notNullField = notNullField;
    }
}
