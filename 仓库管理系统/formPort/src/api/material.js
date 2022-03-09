import router from "../util/router";

//查询所有库存
export function selectAllByClass(current, size, materialCategory) {
  return router({
    url: `/material/${current}/${size}/${materialCategory}`,
    method: "GET"
  });
}

//查询已出库库存
export function selectAllOut(current, size, materialCategory) {
  return router({
    url: `/material/${current}/${size}/${materialCategory}?out`,
    method: "GET"
  });
}

//编辑
export function editMaterial(obj) {
  return router({
    url: `/material`,
    method: "PUT",
    data: obj
  });
}

//出库
export function deleteMaterial(obj) {
  return router({
    url: `/material`,
    method: "DELETE",
    data: obj
  });
}

//入库
export function addMaterial(obj) {
  return router({
    url: `/material`,
    method: "POST",
    data: obj
  });
}

//恢复
export function updateStatus(obj) {
  return router({
    url: `/material?recover`,
    method: "PUT",
    data: obj
  });
}
