package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.team.sear.kcpt.DataBinderMapperImpl());
  }
}
