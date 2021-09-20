/**
 * Tất cả những method trong package này đều bị chặn và kiểm tra phân quyền bằng aop ví dụ
 * {@link com.regitiny.catiny.aop.AdvanceRepositoryAspectService }
 * nếu không có quyền đọc ghi thì người dùng hiện tại cũng sẽ không nhận được kết quả
 * <p>
 * nếu không muốn bị chặn thì sử dụng các method trong {@link com.regitiny.catiny.advance.repository}
 */
package com.regitiny.catiny.advance.repository.base;