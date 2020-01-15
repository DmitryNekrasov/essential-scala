object JsonExample extends App {
  sealed trait JsonValue {
    final def asString: String =
      this match {
        case StringValue(value) => s""""$value""""
        case IntValue(value) => s"$value"
        case ArrayValue(jsonValueArray) => s"[${jsonValueArray.asString}]"
        case Json(jsonFieldList) => s"{${jsonFieldList.asString}}"
        case TrueValue => "true"
        case FalseValue => "false"
        case NullValue => "null"
      }
  }
  final case class StringValue(value: String) extends JsonValue
  final case class IntValue(value: Int) extends JsonValue
  final case class ArrayValue(array: JsonValueArray) extends JsonValue
  final case class Json(jsonFieldList: JsonFieldList) extends JsonValue
  sealed trait BooleanValue extends JsonValue
  final case object TrueValue extends BooleanValue
  final case object FalseValue extends BooleanValue
  final case object NullValue extends JsonValue

  final case class JsonField(key: String, value: JsonValue) {
    def asString = s""""$key": ${value.asString}"""
  }

  sealed trait JsonFieldList {
    final def asString: String =
      this match {
        case JsonFieldPair(fie, JsonFieldListNil) => s"${fie.asString}"
        case JsonFieldPair(field, list) => s"${field.asString}, ${list.asString}"
        case JsonFieldListNil => ""
      }
  }
  final case class JsonFieldPair(jsonField: JsonField, jsonFieldList: JsonFieldList) extends JsonFieldList
  final case object JsonFieldListNil extends JsonFieldList

  sealed trait JsonValueArray {
    final def asString: String =
      this match {
        case JsonValuePair(value, JsonValueArrayNil) => s"${value.asString}"
        case JsonValuePair(value, array) => s"${value.asString}, ${array.asString}"
        case JsonValueArrayNil => ""
      }
  }
  final case class JsonValuePair(jsonValue: JsonValue, jsonValueArray: JsonValueArray) extends JsonValueArray
  final case object JsonValueArrayNil extends JsonValueArray

  val json = Json(
    JsonFieldPair(
      JsonField("name", StringValue("Dmitry")),
      JsonFieldPair(
        JsonField("age", IntValue(26)),
        JsonFieldPair(
          JsonField("array", ArrayValue(
            JsonValuePair(
              StringValue("qqq"),
              JsonValuePair(
                StringValue("www"),
                JsonValuePair(
                  StringValue("eee"),
                  JsonValueArrayNil
                )
              )
            )
          )),
          JsonFieldPair(
            JsonField("json", Json(
              JsonFieldPair(
                JsonField("first", IntValue(123)),
                JsonFieldPair(
                  JsonField("second", TrueValue),
                  JsonFieldPair(
                    JsonField("third", NullValue),
                    JsonFieldListNil
                  )
                )
              )
            )),
            JsonFieldListNil
          )
        )
      )
    )
  )

  println(json.asString)
}
